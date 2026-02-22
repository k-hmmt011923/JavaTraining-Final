package com.example.taskapp.task.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.taskapp.task.dto.TaskForm;
import com.example.taskapp.task.entity.Task;
import com.example.taskapp.task.service.TaskService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskViewController {

    private final TaskService taskService;

    public TaskViewController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 一覧（発展：sort/direction）
    @GetMapping
    public String index(
        @RequestParam(defaultValue = "id") String sort,
        @RequestParam(defaultValue = "asc") String direction,
        Model model
    ) {
        List<Task> tasks = taskService.findAll(sort, direction);
        model.addAttribute("tasks", tasks);
        model.addAttribute("sort", sort);
        model.addAttribute("direction", direction);
        return "tasks/index";
    }

    // 新規作成フォーム
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("taskForm", new TaskForm());
        model.addAttribute("mode", "new");
        return "tasks/form";
    }

    // 登録（PRG + Flash）
    @PostMapping
    public String create(
            @Valid @ModelAttribute("taskForm") TaskForm form,
            BindingResult bindingResult,
            RedirectAttributes ra,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "new");
            return "tasks/form";
        }
        taskService.create(form.getTitle());
        ra.addFlashAttribute("success", "タスクを登録しました");
        return "redirect:/tasks";
    }

    // 編集フォーム（存在しないID→例外→404）
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id);
        TaskForm form = new TaskForm();
        form.setTitle(task.getTitle());
        form.setDone(task.isCompleted());

        model.addAttribute("taskForm", form);
        model.addAttribute("taskId", id);
        model.addAttribute("mode", "edit");
        return "tasks/form";
    }

    // 更新（PRG + Flash）
    @PostMapping("/{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("taskForm") TaskForm form,
            BindingResult bindingResult,
            RedirectAttributes ra,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("taskId", id);
            model.addAttribute("mode", "edit");
            return "tasks/form";
        }
        taskService.update(id, form.getTitle(), form.isDone());
        ra.addFlashAttribute("success", "更新しました");
        return "redirect:/tasks";
    }

    // 削除
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        taskService.delete(id);
        ra.addFlashAttribute("success", "削除しました");
        return "redirect:/tasks";
    }

    // 完了切替
    @PostMapping("/{id}/toggle")
    public String toggle(@PathVariable Long id, RedirectAttributes ra) {
        taskService.toggle(id);
        ra.addFlashAttribute("success", "完了状態を切り替えました");
        return "redirect:/tasks";
    }
}
