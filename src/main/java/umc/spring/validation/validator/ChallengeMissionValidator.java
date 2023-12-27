package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.validation.annotation.ValidChallengeMission;
import umc.spring.web.dto.MemberRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class ChallengeMissionValidator implements ConstraintValidator<ValidChallengeMission, MemberRequestDTO.ChallengeDto> {

    private final MemberMissionRepository memberMissionRepository;


    @Override
    public boolean isValid(MemberRequestDTO.ChallengeDto challengeDto, ConstraintValidatorContext context) {
        boolean isValid = memberMissionRepository.findByMemberIdAndMissionId(
                challengeDto.getMemberId(),
                challengeDto.getMissionId()
        ).isEmpty();

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_CHALLENGED.toString()).addConstraintViolation();
        }

        return isValid;

    }
}
