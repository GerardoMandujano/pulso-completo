package mx.com.gmr.pulsogym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.gmr.pulsogym.mapper.OnboardingTokenMapper;
import mx.com.gmr.pulsogym.model.OnboardinToken;
@Repository
public interface OnboardingTokenRepository extends JpaRepository<OnboardinToken, Long> {
	
	OnboardinToken findByToken(String token);
	
	
	
	

}
