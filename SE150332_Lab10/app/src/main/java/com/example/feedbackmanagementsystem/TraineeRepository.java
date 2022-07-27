package com.example.feedbackmanagementsystem;

public class TraineeRepository {
    public static TraineeService getTraineeService(){
        return APIClient.getClient().create(TraineeService.class);
    }
}
