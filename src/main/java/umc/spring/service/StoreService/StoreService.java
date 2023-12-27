package umc.spring.service.StoreService;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.StoreRequestDTO;


public interface StoreService {

    // 특정 지역에 가게 추가하기 API
    Store addStoreToRegion(StoreRequestDTO storeRequestDTO);

    Review addReviewToStore(ReviewRequestDTO reviewDTO, Long storeId);
}
