package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/ruleName")
public class RuleNameController {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    // 1️⃣ Liste des RuleName
    @GetMapping("/list")
    public String home(Model model) {
        model.addAttribute("rulenames", ruleNameRepository.findAll());
        return "ruleName/list";
    }

    // 2️⃣ Formulaire ajout
    @GetMapping("/add")
    public String addRuleForm(RuleName ruleName) {
        return "ruleName/add";
    }

    // 3️⃣ Validation ajout
    @PostMapping("/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ruleNameRepository.save(ruleName);
            return "redirect:/ruleName/list";
        }
        return "ruleName/add";
    }

    // 4️⃣ Formulaire mise à jour
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid RuleName Id:" + id));
        model.addAttribute("rulename", ruleName);
        return "ruleName/update";
    }

    // 5️⃣ Validation mise à jour
    @PostMapping("/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            ruleName.setId(id); // conserver l'id pour Thymeleaf
            return "ruleName/update";
        }
        ruleNameRepository.save(ruleName);
        return "redirect:/ruleName/list";
    }

    // 6️⃣ Suppression
    @GetMapping("/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid RuleName Id:" + id));
        ruleNameRepository.delete(ruleName);
        return "redirect:/ruleName/list";
    }
}
