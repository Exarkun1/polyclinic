package com.propcool.polyclinic.controllers;

import com.propcool.polyclinic.dto.EditAppointmentRequest;
import com.propcool.polyclinic.exceptions.NotAuthorizationException;
import com.propcool.polyclinic.models.Appointment;
import com.propcool.polyclinic.models.Status;
import com.propcool.polyclinic.services.AppointmentService;
import com.propcool.polyclinic.services.DoctorService;
import com.propcool.polyclinic.services.PatientService;
import com.propcool.polyclinic.utils.security.SecurityUtil;
import com.propcool.polyclinic.validation.annotations.AfterAppointment;
import com.propcool.polyclinic.validation.annotations.ExistPatient;
import com.propcool.polyclinic.validation.annotations.ExistAppointment;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Контроллер запросов докторов
 * */
@Controller
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
    @GetMapping("/main")
    public String mainPage() {
        return "doctor/main";
    }

    @GetMapping("/patients")
    public String patients(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("patients", patientService.getPatients(name));
        return "doctor/patients";
    }

    @GetMapping("/{id}/history")
    public String history(@PathVariable("id") @Valid @ExistPatient Long patientId, Model model) {
        model.addAttribute("appointments", appointmentService.getPatientHistoryWithDoctor(patientId, securityUtil.getDetails()));
        model.addAttribute("patient", patientService.getPatient(patientId));
        return "doctor/history";
    }

    @GetMapping("/appointments")
    public String appointments(Model model) {
        model.addAttribute("appointments", appointmentService.getDoctorExpAppointments(securityUtil.getDetails()));
        model.addAttribute("curDateTime", LocalDateTime.now());
        return "doctor/appointments";
    }

    @GetMapping("/edit/appointment/{id}")
    public String editAppointment(@PathVariable("id") @Valid @ExistAppointment @AfterAppointment Long id, Model model) {
        Appointment appointment = appointmentService.getAppointment(id);
        model.addAttribute("appointment", appointment);
        model.addAttribute("appointmentRequest", appointment.getEditAppointmentRequest());
        model.addAttribute("statuses", Status.valuesWithoutExpectation());
        return "/doctor/edit_appointment";
    }

    @PatchMapping("/edit/appointment/{id}")
    public String editAppointment(
            @ModelAttribute @Valid EditAppointmentRequest appointmentRequest, BindingResult errors,
            @PathVariable("id") @Valid @ExistAppointment @AfterAppointment Long id, Model model
    ) {
        if(errors.hasErrors()) { // В случае неверных данных вновь загружать страницу
            model.addAttribute("appointment", appointmentService.getAppointment(id));
            model.addAttribute("statuses", Status.valuesWithoutExpectation());
            return "/doctor/edit_appointment";
        }
        appointmentService.editAppointment(id, appointmentRequest);
        return "redirect:/doctor/appointments";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("doctor", doctorService.getDoctor(securityUtil.getDetails()));
        return "doctor/profile";
    }

    @ExceptionHandler
    public String notAuthorization(NotAuthorizationException e) {
        return "redirect:/auth/login";
    }

    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final SecurityUtil securityUtil;
}
