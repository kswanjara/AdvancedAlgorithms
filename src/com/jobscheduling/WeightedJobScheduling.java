package com.jobscheduling;

import java.util.*;

public class WeightedJobScheduling {
    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(3, 10, 20));
        jobs.add(new Job(1, 2, 50));
        jobs.add(new Job(6, 19, 100));
        jobs.add(new Job(2, 100, 200));

        WeightedJobScheduling w = new WeightedJobScheduling();
        System.out.println("Max value obtainable : " + w.maxValue(jobs));
    }

    public int maxValue(List<Job> jobs) {
        int[] dpTable = new int[jobs.size()];
        Collections.sort(jobs);

        dpTable[0] = jobs.get(0).getValue();

        for (int i = 1; i < jobs.size(); i++) {
            int tempVal = jobs.get(i).getValue();
            int j = getNextNonConflictingJob(jobs, i);
            if (j != -1) {
                tempVal += jobs.get(j).getValue();
            }

            dpTable[i] = Math.max(tempVal, dpTable[i - 1]);
        }

        return dpTable[jobs.size() - 1];
    }

    public int getNextNonConflictingJob(List<Job> jobs, int index) {
        for (int i = index - 1; i >= 0; i--) {
            if (jobs.get(i).getEndTime() <= jobs.get(index).getStartTime())
                return i;
        }
        return -1;
    }
}

class Job implements Comparable<Job> {
    private Integer startTime;
    private Integer endTime;
    private Integer value;

    public Job(int startTime, int endTime, int value) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.value = value;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return startTime == job.startTime &&
                endTime == job.endTime &&
                value == job.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, value);
    }

    @Override
    public int compareTo(Job o) {
        return this.endTime.compareTo(o.endTime);
    }
}