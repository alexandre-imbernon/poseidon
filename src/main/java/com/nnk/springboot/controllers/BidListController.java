package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/bidList")
public class BidListController {

    @Autowired
    private BidListRepository bidListRepository;

    // LIST
    @GetMapping("/list")
    public String home(Model model) {
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "bidList/list";
    }

    // GET ADD FORM
    @GetMapping("/add")
    public String addBidForm(Model model) {
        model.addAttribute("bidList", new BidList());
        return "bidList/add";
    }

    // SUBMIT ADD
    @PostMapping("/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidList bidList,
                           BindingResult result) {

        if (result.hasErrors()) {
            return "bidList/add";
        }

        bidListRepository.save(bidList);
        return "redirect:/bidList/list";
    }

    // GET UPDATE FORM
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bid = bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));

        model.addAttribute("bidList", bid);
        return "bidList/update";
    }

    // SUBMIT UPDATE
    @PostMapping("/update/{id}")
    public String updateBid(@PathVariable("id") Integer id,
                            @Valid @ModelAttribute("bidList") BidList bidList,
                            BindingResult result) {

        if (result.hasErrors()) {
            bidList.setBidListId(id);
            return "bidList/update";
        }

        bidListRepository.save(bidList);
        return "redirect:/bidList/list";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        BidList bid = bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));

        bidListRepository.delete(bid);
        return "redirect:/bidList/list";
    }
}
