package com.farhanian.cs.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        if (schedule == null || schedule.isEmpty()) {
            return new ArrayList<>();
        }
        List<Interval> all = new ArrayList<>();
        for (int i = 0; i < schedule.size(); i++) {
            List<Interval> current = schedule.get(i);
            for (int j = 0; j < current.size(); j++) {
                all.add(current.get(j));
            }
        }

        Collections.sort(all, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return Integer.compare(a.start, b.start);
            }
        });
        List<Interval> output = new ArrayList<>();
        Interval latest = all.get(0);
        for (int i = 1; i < all.size(); i++) {
            Interval current = all.get(i);
            if (latest.end < current.start) {
                output.add(new Interval(latest.end, current.start));
            }
            latest = (latest.end < current.end) ? current : latest;
        }

        return output;

    }

    private static class Interval {
        public int start;
        public int end;

        public Interval() {
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
