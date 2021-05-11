package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Store
 *
 * @author Vladislav Nechaev
 * @since 28.04.2021
 */

public class Store {

    private static final Store INST = new Store();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private static AtomicInteger POST_ID = new AtomicInteger(4);

    private Store() {
        posts.put(1,
                new Post(1, "Junior Java Job",
                        "Work experience 1 year.",
                        LocalDate.now().minus(2, ChronoUnit.DAYS)));
        posts.put(2,
                new Post(2, "Middle Java Job",
                        "Work experience 2 years.",
                        LocalDate.now().minus(1, ChronoUnit.DAYS)));
        posts.put(3,
                new Post(3, "Senior Java Job",
                        "Work experience 5 years.",
                        LocalDate.now()));
        candidates.put(1, new Candidate(1, "Junior Java"));
        candidates.put(2, new Candidate(2, "Middle Java"));
        candidates.put(3, new Candidate(3, "Senior Java"));
    }

    public void save(Post post) {
        post.setId(POST_ID.incrementAndGet());
        posts.put(post.getId(),post);
    }

    public static Store instOf() {
        return INST;
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }
}
