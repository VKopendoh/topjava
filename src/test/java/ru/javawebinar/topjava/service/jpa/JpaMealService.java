package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractMealService;

@ActiveProfiles(Profiles.JPA)
public class JpaMealService extends AbstractMealService {
}
