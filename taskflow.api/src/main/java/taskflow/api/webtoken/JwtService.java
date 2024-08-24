package taskflow.api.webtoken;

/*
 * @(#)JwtService.java 1.0 22/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Servicio de autenticacion para JWT
 *
 * @author eliezer.navarro
 * @version 1.0 | 22/08/2024
 * @since 1.0
 */

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	//KEY FOR AUTHENTICATION
	//Use generateSecretKey method from de test folder to generate a new key
	private static final String API_ACCESS = "C40112FEBE19BE80A60192D3995B92CCEBDEE6BEED6895C2C6ACA76703B2F5D0556DD998A48DAD26F5F1B25776A3EFA12E238E99FEDCE2EF56F93D695BCE2E2E";
	private static final long VALIDITY = TimeUnit.MINUTES.toMillis(60);
	
	public String generateToken(UserDetails userDetails) {
		Map<String,String> claims = new HashMap<>();
		claims.put("iss","bookmaster.api");
		return Jwts.builder()
			.claims(claims)
			.subject(userDetails.getUsername())
			.issuedAt(Date.from(Instant.now()))
			.expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
			.signWith(generateKey())
			.compact();
	}
	
	private SecretKey generateKey() {
		byte[] decodedKey = Base64.getDecoder().decode(API_ACCESS);
		return Keys.hmacShaKeyFor(decodedKey);
	}
	
	public String extractUsername(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getSubject();
    }

    private Claims getClaims(String jwt) {
        return Jwts.parser()
                 .verifyWith(generateKey())
                 .build()
                 .parseSignedClaims(jwt)
                 .getPayload();
    }

    public boolean isTokenValid(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }

}
