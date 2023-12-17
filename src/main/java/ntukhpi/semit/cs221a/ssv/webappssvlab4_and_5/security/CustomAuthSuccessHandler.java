package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        String targetUrl = determineTargetUrl(authentication);
        String name = authentication.getName();
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        authentication.getAuthorities().forEach(a -> System.out.println(a.getAuthority()));
        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"))) {
            return "/admin";
        } else if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("USER"))) {
            return "/home";
        } else {
            return "/error";
        }
    }
}
