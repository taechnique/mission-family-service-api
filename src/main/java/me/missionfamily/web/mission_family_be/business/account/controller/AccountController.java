package me.missionfamily.web.mission_family_be.business.account.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.missionfamily.web.mission_family_be.business.account.dxo.AccountDxo;
import me.missionfamily.web.mission_family_be.business.account.service.AccountService;
import me.missionfamily.web.mission_family_be.common.data_transfer.MissionResponse;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Slf4j
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/check/duplicate")
    public ResponseEntity<MissionResponse> dupCheck (@RequestParam(value = "user_id") String userId) throws ServiceException {

        MissionResponse missionResponse = accountService.dupCheckById(userId);

        return ResponseEntity.ok().body(missionResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<MissionResponse> registerAccount (@RequestBody @Valid AccountDxo.Request request) throws ServiceException {

        MissionResponse missionResponse = accountService.registerForAccount(request);

        return ResponseEntity.ok().body(missionResponse);
    }


    @PutMapping("/signin")
    public ResponseEntity<MissionResponse> loginAccount (@RequestBody @Valid AccountDxo.Request request) throws ServiceException {

        MissionResponse missionResponse = accountService.signInForAccount(request);

        return ResponseEntity.ok().body(missionResponse);
    }


}
