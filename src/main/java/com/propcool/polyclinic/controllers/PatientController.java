package com.propcool.polyclinic.controllers;

import com.propcool.polyclinic.dto.AppointmentRequest;
import com.propcool.polyclinic.dto.EditPasswordRequest;
import com.propcool.polyclinic.dto.EditProfileRequest;
import com.propcool.polyclinic.exceptions.NotAuthorizationException;
import com.propcool.polyclinic.models.Status;
import com.propcool.polyclinic.services.*;
import com.propcool.polyclinic.utils.security.SecurityUtil;
import com.propcool.polyclinic.validation.annotations.ExistAppointment;
import com.propcool.polyclinic.validation.annotations.ExistDoctor;
import com.propcool.polyclinic.validation.annotations.PatientFreeTime;
import com.propcool.polyclinic.validation.annotations.StatusIs;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер запросов пациентов
 * */
@Controller
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
    @GetMapping("/main")
    public String mainPage() {
        return "patient/main";
    }

    @GetMapping("/doctors")
    public String doctors(@RequestParam(value = "job", required = false) String job, Model model) {
        model.addAttribute("doctors", doctorService.getDoctors(job));
        model.addAttribute("jobs", jobService.getJobs());
        model.addAttribute("selectedJob", job);
        return "patient/doctors";
    }

    @GetMapping("/new/{id}/appointment")
    public String newAppointment(@PathVariable("id") @Valid @ExistDoctor Long doctorId, Model model) {
        model.addAttribute("appointmentRequest", new AppointmentRequest(doctorId));
        model.addAttribute("freeTimes", appointmentService.getTwoWeeksFreeTime(doctorId));
        return "patient/new_appointment";
    }

    @PostMapping("/new/{id}/appointment")
    public String newAppointment(
            @ModelAttribute @Valid AppointmentRequest appointmentRequest, BindingResult errors,
            @PathVariable("id") Long doctorId, Model model
    ) {
        if(errors.hasErrors()) { // В случае неверных данных вновь загружать страницу
            appointmentRequest.setDoctorId(doctorId);
            appointmentRequest.setDate(null);
            appointmentRequest.setTime(null);
            model.addAttribute("freeTimes", appointmentService.getTwoWeeksFreeTime(doctorId));
            return "patient/new_appointment";
        }
        appointmentService.addAppointment(doctorId, appointmentRequest, securityUtil.getDetails());
        return "redirect:/patient/main";
    }

    @GetMapping("/appointments")
    public String appointments(Model model) {
        model.addAttribute("appointments", appointmentService.getPatientAppointments(securityUtil.getDetails()));
        model.addAttribute("expectation", Status.Expectation);
        return "/patient/appointments";
    }

    @DeleteMapping("/delete/appointment/{id}")
    public String deleteAppointment(@PathVariable("id") @Valid @ExistAppointment @StatusIs(Status.Expectation) Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/patient/appointments";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("patient", patientService.getPatient(securityUtil.getDetails()));
        return "/patient/profile";
    }

    @GetMapping("/edit/profile")
    public String editProfile(Model model) {
        model.addAttribute("editProfileRequest", patientService.getEditProfileRequest(securityUtil.getDetails()));
        model.addAttribute("editPasswordRequest", new EditPasswordRequest());
        return "/patient/edit_profile";
    }

    @PatchMapping("/edit/profile")
    public String editProfile(
            @ModelAttribute @Valid EditProfileRequest editRequest,
            BindingResult errors, Model model
    ) {
        if(errors.hasErrors()) { // В случае неверных данных вновь загружать страницу
            model.addAttribute("editPasswordRequest", new EditPasswordRequest());
            return "patient/edit_profile";
        }
        patientService.editPatient(editRequest, securityUtil.getDetails());
        return "redirect:/patient/profile";
    }

    @PatchMapping("/edit/password")
    public String editPassword(
            @ModelAttribute @Valid EditPasswordRequest editPasswordRequest,
            BindingResult errors, Model model
    ) {
        if(errors.hasErrors()) { // В случае неверных данных вновь загружать страницу
            model.addAttribute("editProfileRequest", patientService.getEditProfileRequest(securityUtil.getDetails()));
            return "patient/edit_profile";
        }
        userDataService.editPassword(editPasswordRequest, securityUtil.getDetails());
        return "redirect:/patient/profile";
    }

    @ExceptionHandler
    public String notAuthorization(NotAuthorizationException e) {
        return "redirect:/auth/login";
    }

    private final UserDataService userDataService;
    private final DoctorService doctorService;
    private final JobService jobService;
    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final SecurityUtil securityUtil;
}
