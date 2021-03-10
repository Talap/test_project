package com.example.test_project.controller;


import com.example.test_project.controller.exception.ServiceException;
import com.example.test_project.models.Requests.ClientRequest;
import com.example.test_project.models.responses.SuccessResponse;
import com.example.test_project.service.ClientService;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collection;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;


@Service
@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
public class ClientController extends BaseController {
    private ClientService clientService;
    private final Multimap<Long, DeferredResult<Long>> watchRequests = Multimaps.synchronizedSetMultimap(HashMultimap.create());



//    @RequestMapping(value = "/watch/{namespace}", method = RequestMethod.GET, produces = "text/html")
//    public DeferredResult<String> watch(@PathVariable("namespace") String namespace) {
//        LOGGER.info("Request received");
//        ResponseEntity<String>
//                NOT_MODIFIED_RESPONSE = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//        DeferredResult<String> deferredResult = new DeferredResult<>(5000L, NOT_MODIFIED_RESPONSE);
//        // When the deferredResult is completed (whether it is timeout or abnormal or normal completion), remove the corresponding watch key in watchRequests
//        deferredResult.onCompletion(() -> {
//            LOGGER.info("remove key:" + namespace);
//            watchRequests.remove(namespace, deferredResult);
//        });
//        deferredResult.onTimeout(() -> {
//            LOGGER.info("onTimeout()");
//        });
//        watchRequests.put(namespace, deferredResult);
//        LOGGER.info("Servlet thread released");
//        return deferredResult;
//    }
//
//    // Simulation release namespace configuration
//    @RequestMapping(value = "/publish/{namespace}", method = RequestMethod.GET, produces = "text/html")
//    public Object publishConfig(@PathVariable("namespace") String namespace) {
//        if (watchRequests.containsKey(namespace)) {
//            Collection<DeferredResult<String>> deferredResults = watchRequests.get(namespace);
//            long time = System.currentTimeMillis();
//            // Notify all watch changes in the long wheel training configuration change of the namespace change
//            for (DeferredResult<String> deferredResult : deferredResults) {
//                // deferredResult Once the setResult() method is executed, the DeferredResult is normally completed and the result is immediately returned to the client.
//                deferredResult.setResult(namespace + " changed:" + time);
//            }
//
//        }
//        return "success";
//    }

    @PostMapping(value = "/add")
    @ApiOperation("Добавление клиента")
    public ResponseEntity<?> add(@ApiParam("Добавление нового клиента")
                                        @RequestParam Long id,
                                        @RequestBody ClientRequest clientRequest)  {

        clientService.createClient(clientRequest);
                if (watchRequests.containsKey(id)) {
            Collection<DeferredResult<Long>> deferredResults = watchRequests.get(id);
            long time = System.currentTimeMillis();
            // Notify all watch changes in the long wheel training configuration change of the namespace change
            for (DeferredResult<Long> deferredResult : deferredResults) {
                // deferredResult Once the setResult() method is executed, the DeferredResult is normally completed and the result is immediately returned to the client.
                deferredResult.setResult(id);
            }

        }
        return buildResponse(HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    @ApiOperation("Изменение клиента")
    public  DeferredResult<Long> update(@ApiParam("Обновление клиента")
                                        @RequestParam Long id,
                                        @RequestBody ClientRequest clientRequest)  {
        ResponseEntity<?>
                NOT_MODIFIED_RESPONSE = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        DeferredResult<Long> deferredResult = new DeferredResult<>(20000L, NOT_MODIFIED_RESPONSE);

        deferredResult.onCompletion(() -> {
            LOGGER.info("remove key:" + id);
            watchRequests.remove(id, deferredResult);
            clientService.updateClient(clientRequest, id);
        });
        deferredResult.onTimeout(() -> {
            LOGGER.info("onTimeout()");
        });
        watchRequests.put(id, deferredResult);

        return deferredResult;
    }

    @GetMapping(value = "/get/all")
    @ApiOperation("Получение списка всех клиентов")
    public ResponseEntity<?> getAll(){
        return buildResponse(clientService.getAllClients(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление клиента и его контактов по id")
    public  ResponseEntity<?> deleteWorkplaceId(@RequestParam Long id) throws ServiceException {
        clientService.deleteClientById(id);
        return buildResponse(SuccessResponse
                .builder().message("deleted")
                .build(), HttpStatus.OK);
    }


    @GetMapping(value = "/get/single")
    @ApiOperation("Получение одного клиента с контактами")
    public ResponseEntity<?> getOneClient(@RequestParam Long id){
        return buildResponse(clientService.getClientWithAllContactInfo(id), HttpStatus.OK);
    }

}
