package log.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import pochemon.log.Info;
import pochemon.log.LogServiceGrpc.LogServiceImplBase;
import pochemon.log.Saved;

@GrpcService
public class LogServiceImpl extends LogServiceImplBase {

	@Override
	public void logAuth(Info request, StreamObserver<Saved> responseObserver) {

		Boolean result = this.saveInfo(request);

		Saved response = Saved.newBuilder().setValidation(result).build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();

	}

	@SuppressWarnings("unchecked")
	private boolean saveInfo(Info info) {

		String userDirectory = new File("").getAbsolutePath() + "\\authLog\\log.log";
		FileWriter file;
		try {
			file = new FileWriter(userDirectory, true);
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("userId", info.getUsername());
			jsonObject.put("date", info.getDate());
			file.write(jsonObject.toJSONString());
			file.write(System.lineSeparator());
			file.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

}
