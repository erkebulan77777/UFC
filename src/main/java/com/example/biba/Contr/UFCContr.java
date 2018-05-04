package com.example.biba.Contr;

import com.example.biba.Model.Box;
import com.example.biba.Model.Mma;
import com.example.biba.repasitory.MmaRepasitory;
import com.example.biba.repasitory.BoxRepasitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UFCContr {

    @Autowired
    private BoxRepasitory boxRepasitory;

    @Autowired
    private MmaRepasitory MmaRepasitory;

    @RequestMapping("/box/{id}")
    public String box(@PathVariable("id")int id, Model model) {
        model.addAttribute("box", boxRepasitory.findById(id).get());
        model.addAttribute("mma", MmaRepasitory.findAll());
        return "box";
    }
    @RequestMapping(value="/boxs", method = RequestMethod.GET)
    public String boxsList(Model model) {
        model.addAttribute("boxs", boxRepasitory.findAll());
        return "boxs";
    }

    @RequestMapping(value = "/boxs", method = RequestMethod.POST)
    public String boxsAdd(@RequestParam String email,
                               @RequestParam String firstName, @RequestParam String lastName, Model model) {
        Box newBox = new Box();
        newBox.setEmail(email);
        newBox.setFirstName(firstName);
        newBox.setLastName(lastName);
        boxRepasitory.save(newBox);

        model.addAttribute("box", newBox);
        model.addAttribute("mmas", MmaRepasitory.findAll());
        return "redirect:/box/" + newBox.getId();
    }
    /////////
    @RequestMapping(value = "/box/{id}/mmas", method = RequestMethod.POST)
    public String boxAddClub(@PathVariable Integer id,@RequestParam Integer mmaId, Model model){
        Mma mma = MmaRepasitory.findById(mmaId).get();
        Box box = boxRepasitory.findById(id).get();

        if (box != null){
            if (!box.hasMma(mma)){
                box.getMmas().add(mma);
            }
            boxRepasitory.save(box);
            model.addAttribute("box", boxRepasitory.findById(id).get());
            model.addAttribute("mmas", MmaRepasitory.findAll());
            return "redirect:/box/" + box.getId();
        }

        model.addAttribute("boxs", boxRepasitory.findAll());
        return "redirect:/boxs";
    }
    @RequestMapping(value = "/mmas", method = RequestMethod.GET)
    public String mmasAdd(Model model){
        model.addAttribute("mma", new Mma());
        return "mma";
    }
    @RequestMapping(value = "/mmas" , method = RequestMethod.POST)
    public String bmwsAdd(@ModelAttribute("mma") @Valid Mma mma, BindingResult result){
        if (result.hasErrors()){
            return "mma";
        }
        MmaRepasitory.save(mma);
        return "redirect:/boxs";
    }
}
