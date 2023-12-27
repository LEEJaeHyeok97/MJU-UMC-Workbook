package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.enums.Gender;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreServiceImpl implements StoreService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public Store addStoreToRegion(StoreRequestDTO storeRequestDTO) {
        StoreRequestDTO.StoreDTO storeDTO = storeRequestDTO.getStore();
        StoreRequestDTO.RegionDTO regionDTO = storeRequestDTO.getRegion();

        Region region = regionRepository.findByName(regionDTO.getName());

        Store store = Store.builder()
                .name(storeDTO.getName())
                .address(storeDTO.getAddress())
                .region(region)
                .build();



        return storeRepository.save(store);
    }

    @Override
    @Transactional
    public Review addReviewToStore(ReviewRequestDTO reviewDTO, Long storeId) {
        Member member1 = Member.builder()
                .id(1L)
                .build();

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        Member member = memberRepository.findById(member1.getId())
                .orElseThrow(() -> new RuntimeException("Member not found"));


        Review review = Review.builder()
                .score(reviewDTO.getScore())
                .title(reviewDTO.getTitle())
                .store(store)
                .member(member)
                .build();


        return reviewRepository.save(review);
    }


}
