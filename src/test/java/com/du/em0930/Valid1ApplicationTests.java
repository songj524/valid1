package com.du.em0930;

import com.du.em0930.entity.MyUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Valid1ApplicationTests {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void testPersist() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();  // 트랜잭션 시작

        MyUser user = MyUser.builder()  // 객체 생성
                .name("강아지")
                .email("dog@gmail.com")
                .password("123456")
                .build();

        em.persist(user); // 영속성이 생김

//        user.setName("테스트");

        em.flush();    // DB에 즉시 반영
        transaction.commit(); // 트랜잭션 커밋

        em.close();  // 엔티티 매니저 닫지
    }

    @Test
    void testTemplate() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();  // 트랜잭션 시작



        em.flush();    // DB에 즉시 반영
        transaction.commit(); // 트랜잭션 커밋

        em.close();  // 엔티티 매니저 닫지
    }

    @Test
    void testFind() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();  // 트랜잭션 시작

        MyUser user = em.find(MyUser.class, 1L);
        System.out.println(user);
        
        user.setName("최하나");

        em.flush();    // DB에 즉시 반영
        transaction.commit(); // 트랜잭션 커밋

        em.close();  // 엔티티 매니저 닫지
    }

    @Test
    void testMerge() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();  // 트랜잭션 시작

        MyUser user = MyUser.builder()
                .id(1L)
                .name("관리자")
                .email("admin@gmail.com")
                .password("123456")
                .build();

        em.merge(user); // 영속성이 생김

        user.setName("테스트");

        em.flush();    // DB에 즉시 반영
        transaction.commit(); // 트랜잭션 커밋

        em.close();  // 엔티티 매니저 닫지
    }

    @Test
    void testRemove() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();  // 트랜잭션 시작

        MyUser user = em.find(MyUser.class, 2L);

        em.remove(user);

        em.flush();    // DB에 즉시 반영
        transaction.commit(); // 트랜잭션 커밋

        em.close();  // 엔티티 매니저 닫지
    }

    @Test
    void testFindAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();  // 트랜잭션 시작

        List<MyUser> list = em.createQuery("SELECT e FROM MyUser e", MyUser.class)
                .getResultList();
        for (MyUser user : list) {
            System.out.println(user);
            user.setName("가나다");
        }

        em.flush();    // DB에 즉시 반영
        transaction.commit(); // 트랜잭션 커밋

        em.close();  // 엔티티 매니저 닫지
    }
}