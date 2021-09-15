package project_dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project_dz.dao.TovarDao;
import project_dz.entity.Tovar;
import project_dz.service.TovarService;

import java.util.List;

@RestController
@RequestMapping("rest/tovar")
public class TovarController {
    @Autowired
    TovarService tovarService;

    @GetMapping
    public List<Tovar> getAllTovar(){
        return tovarService.getAllTovar();
    }

   /* @PostMapping
    public void addTovar(@RequestBody Tovar tovar){
        tovarService.a;
    }*/

    @PutMapping
    public void update(@RequestBody Tovar tovar){
        tovarService.updateTovar(tovar);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id){
        tovarService.deleteTovar(id);
    }

    @PutMapping(value = "/{id}/reserved")
    public void reservedTovar(@PathVariable Long id, @RequestParam Boolean reserved){
        tovarService.reservedTovar(id, reserved);
    }

    @GetMapping(value = "/{id}")
    public Tovar getTovarById(@PathVariable Long id){
        return tovarService.getTovarById(id);
    }
}
