package com.example.biba.Contr;

import com.example.biba.Model.Samsa;
import com.example.biba.Model.Tort;
import com.example.biba.repasitory.TortRepasitory;
import com.example.biba.repasitory.SamsaRepasitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class KulinariaContr {

    @Autowired
    private SamsaRepasitory samsaRepasitory;

    @Autowired
    private TortRepasitory tortRepasitory;

    @RequestMapping("/samsa/{id}")
    public String samsa(@PathVariable("id")int id, Model model) {
        model.addAttribute("samsa", samsaRepasitory.findById(id).get());
        model.addAttribute("torts", tortRepasitory.findAll());
        return "samsa";
    }
    @RequestMapping(value="/samsas", method = RequestMethod.GET)
    public String samsasList(Model model) {
        model.addAttribute("samsas", samsaRepasitory.findAll());
        return "samsas";
    }

    @RequestMapping(value = "/samsas", method = RequestMethod.POST)
    public String samsasAdd(@RequestParam String email,
                               @RequestParam String firstName, @RequestParam String lastName, Model model) {
        Samsa newSamsa = new Samsa();
        newSamsa.setEmail(email);
        newSamsa.setFirstName(firstName);
        newSamsa.setLastName(lastName);
        samsaRepasitory.save(newSamsa);

        model.addAttribute("samsa", newSamsa);
        model.addAttribute("torts", tortRepasitory.findAll());
        return "redirect:/samsa/" + newSamsa.getId();
    }
    /////////
    @RequestMapping(value = "/samsa/{id}/torts", method = RequestMethod.POST)
    public String samsaAddClub(@PathVariable Integer id,@RequestParam Integer tortId, Model model){
        Tort tort = tortRepasitory.findById(tortId).get();
        Samsa samsa = samsaRepasitory.findById(id).get();

        if (samsa != null){
            if (!samsa.hasTort(tort)){
                samsa.getTorts().add(tort);
            }
            samsaRepasitory.save(samsa);
            model.addAttribute("samsa", samsaRepasitory.findById(id).get());
            model.addAttribute("torts", tortRepasitory.findAll());
            return "redirect:/samsa/" + samsa.getId();
        }

        model.addAttribute("samsas", samsaRepasitory.findAll());
        return "redirect:/samsas";
    }
    @RequestMapping(value = "/torts", method = RequestMethod.GET)
    public String tortsAdd(Model model){
        model.addAttribute("tort", new Tort());
        return "tort";
    }
    @RequestMapping(value = "/torts" , method = RequestMethod.POST)
    public String tortsAdd(@ModelAttribute("tort") @Valid Tort tort, BindingResult result){
        if (result.hasErrors()){
            return "tort";
        }
        tortRepasitory.save(tort);
        return "redirect:/samsas";
    }
}
