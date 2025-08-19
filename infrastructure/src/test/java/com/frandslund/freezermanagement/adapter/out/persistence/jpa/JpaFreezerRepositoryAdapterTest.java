package com.frandslund.freezermanagement.adapter.out.persistence.jpa;

import com.frandslund.freezermanagement.adapter.TestProfileWithJpa;
import com.frandslund.freezermanagement.adapter.out.persistence.AbstractFreezerRepositoryTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;

@QuarkusTest
@TestProfile(TestProfileWithJpa.class)
public class JpaFreezerRepositoryAdapterTest extends AbstractFreezerRepositoryTest {
}
