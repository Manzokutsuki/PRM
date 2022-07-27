package com.example.testpe;

public class TraineeRepository {
    public static TraineeService getTraineeService(){
        return APIClient.getClient().create(TraineeService.class);
    }
}
