package com.lipian.shortestpathpoland.voivodeship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Voivodeship {
    private final String name;
    private List<String> neighbors;

    public static Voivodeship getVoivodeshipByName(List<Voivodeship> voivodeships, String name) {
        Optional<Voivodeship> voivodeship = voivodeships.stream()
                .filter(v -> v.name.equals(name))
                .findAny();
        return voivodeship.orElse(null);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Voivodeship voivodeship)) return false;
        return voivodeship.name.equals(name);
    }
}
