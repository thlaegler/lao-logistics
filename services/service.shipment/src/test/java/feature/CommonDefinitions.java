package feature;

import static io.restassured.RestAssured.given;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;

public abstract class CommonDefinitions<T> {

	private RequestSpecification req;
	protected Response resp;

	protected abstract Class<T> getType();

	protected abstract String getHost();

	protected abstract String getService();

	protected T getOne(DataTable table) {
		return table.asList(getType()).get(0);
	}

	protected List<T> getMany(DataTable table) {
		return table.asList(getType());
	}

	protected RequestSpecification newReq() {
		req = given().baseUri(getHost()).basePath(getService()).log().body();
		return req;
	}

	protected RequestSpecification req() {
		return req != null ? req : newReq();
	}

}
