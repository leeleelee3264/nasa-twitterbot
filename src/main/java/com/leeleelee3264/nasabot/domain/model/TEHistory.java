package com.leeleelee3264.nasabot.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "today_earth_hsitory")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TEHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private boolean tweeted;

    @NotNull
    private LocalDate createDate;


    private LocalDateTime tweetedTime;

    @Builder
    public TEHistory(boolean tweeted, LocalDate createDate) {
        this.tweeted = tweeted;
        this.createDate = createDate;
    }

    public void succeed() {
        this.tweeted = true;
        this.tweetedTime = LocalDateTime.now();
    }

}
