package uz.ds.wordfile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ds.wordfile.entity.User;
import uz.ds.wordfile.payload.ApiResponse;
import uz.ds.wordfile.payload.ReqUser;
import uz.ds.wordfile.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }

    public UserDetails getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
    }

    public ApiResponse addRegister(ReqUser reqUser) {
        try {
            Optional<User> optionalUser = userRepository.findByPhoneNumber(reqUser.getPhoneNumber());

            if (!optionalUser.isPresent()) {
                if (reqUser.getPassword().equals(reqUser.getPrePassword())) {
                    userRepository.save(new User(reqUser.getFirstName(),
                            reqUser.getLastName(),
                            reqUser.getMiddleName(),
                            reqUser.getPhoneNumber(), passwordEncoder.encode(reqUser.getPassword())));
                    return new ApiResponse("Ro'yxatdan o'tingiz", "Sign up", "зарегистрироваться", "", true);
                }
                return new ApiResponse("passwordda xato kettingiz", false);
            }
            return new ApiResponse("Siz avval ruyxatdan utgansiz", "Error", "ошибка", "", false);
        }catch (Exception e){
            return new ApiResponse("Xatolik", "Error", "ошибка", "", false);
        }

    }


    public List<User> getUsers(){
        return userRepository.findAll();
    }


}
