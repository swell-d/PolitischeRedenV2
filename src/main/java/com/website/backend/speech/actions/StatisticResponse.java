package com.website.backend.speech.actions;

import java.io.Serializable;
import java.util.Objects;

public class StatisticResponse implements Serializable {
    public String mostSpeeches;
    public String mostSecurity;
    public String leastWordy;

    public StatisticResponse(String mostSpeeches, String mostSecurity, String leastWordy) {
        this.mostSpeeches = mostSpeeches;
        this.mostSecurity = mostSecurity;
        this.leastWordy = leastWordy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticResponse that = (StatisticResponse) o;
        return Objects.equals(mostSpeeches, that.mostSpeeches) &&
                Objects.equals(mostSecurity, that.mostSecurity) &&
                Objects.equals(leastWordy, that.leastWordy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mostSpeeches, mostSecurity, leastWordy);
    }
}
