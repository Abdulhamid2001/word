package uz.ds.wordfile.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uz.ds.wordfile.entity.User;
import uz.ds.wordfile.payload.ApiResponse;
import uz.ds.wordfile.payload.ReqLogin;
import uz.ds.wordfile.payload.ReqUser;
import uz.ds.wordfile.payload.ResToken;
import uz.ds.wordfile.security.JwtTokenProvider;
import uz.ds.wordfile.service.AuthService;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
   @Autowired
   AuthenticationManager authenticationManager;
   @Autowired
   JwtTokenProvider jwtTokenProvider;
   @Autowired
    AuthService authService;


    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody ReqLogin reqLogin) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                reqLogin.getPhoneNumber(),
                reqLogin.getPassword()
        ));
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new ResToken(token));
    }

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody ReqUser reqUser) {
        ApiResponse response = authService.addRegister(reqUser);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping("/lists")
    public HttpEntity<?> getUsers(){
        List<User> userList=authService.getUsers();
        return ResponseEntity.ok(userList);
    }

}
