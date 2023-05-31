package log.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import pochemon.auth.log.Info;
import pochemon.auth.log.LogServiceGrpc.LogServiceImplBase;
import pochemon.auth.log.Saved;

@GrpcService
public class LogService extends LogServiceImplBase{
	
	@Override
	public void logAuth(Info request, StreamObserver<Saved> responseObserver) {
		
		System.out.println(request);
		
		this.saveInfo(request);
		
		Saved response = Saved.newBuilder().setValidation(true).build();
		
		responseObserver.onNext(response);
		responseObserver.onCompleted();
		
	}
	
	@SuppressWarnings("unchecked")
	private void saveInfo(Info info) {
		
		String userDirectory = new File("").getAbsolutePath();
		System.out.println(userDirectory);
//		FileWriter file = new FileWriter("E:/output.json");
//		JSONObject jsonObject = new JSONObject();
//		
//		jsonObject.put("userId", info.getUserId());
//		jsonObject.put("date", info.getDate());
//		file.write(jsonObject.toJSONString());
//		file.close();
		
	}

}
