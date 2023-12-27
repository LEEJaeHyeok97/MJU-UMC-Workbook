package umc.spring.service.FoodCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.FoodCategoryRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodCategoryServiceImpl implements FoodCategoryService{

    private final FoodCategoryRepository foodCategoryRepository;
    @Override
    public boolean existById(Long id) {
        return foodCategoryRepository.existsById(id);
    }
}
