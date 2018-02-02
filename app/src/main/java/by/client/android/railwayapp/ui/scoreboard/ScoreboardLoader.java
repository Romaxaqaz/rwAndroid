package by.client.android.railwayapp.ui.scoreboard;

import java.util.List;

import by.client.android.railwayapp.api.Loader;
import by.client.android.railwayapp.api.RegisterLoader;
import by.client.android.railwayapp.api.SafeRegistrationSendListener;
import by.client.android.railwayapp.api.Stantion;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.ui.BaseAsyncTask;

/**
 * Загрузчик данных для страницы {@link ScoreboardActivity}
 *
 * @author ROMAN PANTELEEV
 */
class ScoreboardLoader implements Loader {

    private Stantion stantion;
    private RegisterLoader registerLoader;

    ScoreboardLoader(Stantion stantion, RegisterLoader registerLoader) {
        this.stantion = stantion;
        this.registerLoader = new SafeRegistrationSendListener(registerLoader);
    }

    @Override
    public void load() {
        new LoadScoreboardAsync(registerLoader).execute(stantion);

//        OriginDestinationInformation originDestinationInformation = new OriginDestinationInformation();
//        originDestinationInformation.setDepartureDate("2018-02-16");
//        originDestinationInformation.setDestinationLocation(new DestinationLocation().withLocationCode("2100170"));
//        originDestinationInformation.setOriginLocation(new OriginLocation().withLocationCode("2100000"));
//        originDestinationInformation.setDepartureTimeRange(new DepartureTimeRange().withFrom("00:00").withTo("24:00"));
//
//        List<OriginDestinationInformation> originDestinationInformationList = new ArrayList<>();
//        originDestinationInformationList.add(originDestinationInformation);
//        Q q = new Q();
//        q.setOriginDestinationInformation(originDestinationInformationList);
//        SearchStantion searchTrainRoute = new SearchStantion().withQ(q).withRqType("railroad");
//
//        new BiletixService().getBiletixAPI().savePost(searchTrainRoute).enqueue(new Callback<Object>() {
//            @Override
//            public void onResponse(Call<Object> call, Response<Object> response) {
//                Object s = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<Object> call, Throwable t) {
//                String s = "";
//            }
//        });
    }

    private static class LoadScoreboardAsync extends BaseAsyncTask<Stantion, List<Train>> {

        private RegisterLoader registerLoader;

        LoadScoreboardAsync(RegisterLoader registerLoader) {
            this.registerLoader = registerLoader;
        }

        @Override
        protected void onStart() {
            registerLoader.onStart();
        }

        @Override
        protected void onCompleted(List<Train> trains) {
            registerLoader.onSuccess(trains);
        }

        @Override
        protected void onFinish(boolean successful) {
            registerLoader.onFinish(successful);
        }

        @Override
        protected void onError(Exception exception) {
            registerLoader.onError(exception);
        }

        @Override
        protected List<Train> runTask(Stantion... param) throws Exception {
            return new ScoreboardParsing(param[0]).pars();
        }
    }
}
