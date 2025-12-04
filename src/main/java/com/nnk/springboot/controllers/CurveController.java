package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;

@Controller
@RequestMapping("/curvePoint")
public class CurveController {

    @Autowired
    private CurvePointRepository curvePointRepository;

    // Liste
    @GetMapping("/list")
    public String home(Model model) {
        model.addAttribute("curvepointLists", curvePointRepository.findAll());
        return "curvePoint/list";
    }

    // Formulaire add
    @GetMapping("/add")
    public String addForm(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    // Validation add
    @PostMapping("/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result) {
        if (result.hasErrors()) {
            return "curvePoint/add";
        }
        curvePoint.setCreationDate(new Timestamp(System.currentTimeMillis()));
        curvePointRepository.save(curvePoint);
        return "redirect:/curvePoint/list";
    }

    // Formulaire update
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curvepoint Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    // Validation update
    @PostMapping("/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                                   BindingResult result) {
        if (result.hasErrors()) {
            curvePoint.setId(id);
            return "curvePoint/update";
        }
        curvePointRepository.save(curvePoint);
        return "redirect:/curvePoint/list";
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id) {
        CurvePoint curvePoint = curvePointRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curvepoint Id:" + id));
        curvePointRepository.delete(curvePoint);
        return "redirect:/curvePoint/list";
    }
}
