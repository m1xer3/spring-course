package ru.danilsibgatyllin.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.danilsibgatyllin.models.User;

public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
//
//    // Identity Map
//    private Map<Long, User> userMap = new ConcurrentHashMap<>();
//
//    private AtomicLong identity = new AtomicLong(0);
//
//    @PostConstruct
//    public void init() {
//        this.insert(new User("user1"));
//        this.insert(new User("user2"));
//        this.insert(new User("user3"));
//    }
//
//    public List<User> findAll() {
//        return new ArrayList<>(userMap.values());
//    }
//
//    public User findById(long id) {
//        return userMap.get(id);
//    }
//
//    public void insert(User user) {
//        long id = identity.incrementAndGet();
//        user.setId(id);
//        userMap.put(id, user);
//    }
//
//    public void update(User user) {
//        userMap.put(user.getId(), user);
//    }
//
//    public void delete(long id) {
//        userMap.remove(id);
//    }

}
