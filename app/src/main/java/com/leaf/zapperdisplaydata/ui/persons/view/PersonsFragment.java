package com.leaf.zapperdisplaydata.ui.persons.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.leaf.zapperdisplaydata.R;
import com.leaf.zapperdisplaydata.data.remote.model.Person;
import com.leaf.zapperdisplaydata.data.remote.model.PersonDetails;
import com.leaf.zapperdisplaydata.ui.persons.PersonsContract;
import com.leaf.zapperdisplaydata.ui.persons.adapter.PersonsAdapter;
import com.leaf.zapperdisplaydata.ui.persons.viewmodel.PersonsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class PersonsFragment extends Fragment implements PersonsContract.View, SwipeRefreshLayout.OnRefreshListener {

    private PersonsViewModel mViewModel;

    private CompositeDisposable subscriptions = new CompositeDisposable();

    private final int DEFAULT_PERSON_ID =1;

    @BindView(R.id.persons_recycler_view)
    RecyclerView personsRecyclerView;

    @BindView(R.id.swipe_persons_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    TextView personName;

    TextView personTeam;

    public static PersonsFragment newInstance() {
        return new PersonsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root;
        int orientation = getOrientation();
        if (isOrientationLandscape(orientation)) {
            root = inflater.inflate(R.layout.layout_people_view, container, false);

            personName = root.findViewById(R.id.person_name);

            personTeam = root.findViewById(R.id.person_team);

        } else {
            root = inflater.inflate(R.layout.fragment_persons, container, false);
        }

        ButterKnife.bind(this, root);

        setupPersonsList(root.getContext());

        return root;
    }

    private int getOrientation() {
        return getResources().getConfiguration().orientation;
    }

    private boolean isOrientationLandscape(int orientation) {
        return orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PersonsViewModel.class);
        swipeRefreshLayout.setOnRefreshListener(this);

        mViewModel.getPersons().observe(getViewLifecycleOwner(), this::updatePersonsList);

        if(isOrientationLandscape(getOrientation())) {
            mViewModel.getPersonDetail(DEFAULT_PERSON_ID)
                    .observe(getViewLifecycleOwner(), this::updatePersonDetails);
        }
    }

    private void navigateToPersonDetails(Person person) {

        Log.v(PersonsFragment.class.getSimpleName(), person.toString());

        Navigation.findNavController(getView())
                .navigate(PersonsFragmentDirections
                        .actionPersonsFragmentToPersonDetailsFragment(person.getId()));
    }


    private void setupPersonsList(final Context context) {
        if (personsRecyclerView == null) {

        } else {
            personsRecyclerView.setLayoutManager(new LinearLayoutManager(context,
                    LinearLayoutManager.VERTICAL, false));

            personsRecyclerView.addItemDecoration(new DividerItemDecoration(context,
                    DividerItemDecoration.VERTICAL));


            personsRecyclerView.setHasFixedSize(true);

            personsRecyclerView.setAdapter(new PersonsAdapter());

        }

        if(isOrientationLandscape(getOrientation())){
            addViewSubscription((((PersonsAdapter) personsRecyclerView.getAdapter())
                    .getPersonListItemClickr()
                    .subscribe(person -> {
                      mViewModel.getPersonDetail(person.getId())
                              .observe(getViewLifecycleOwner(), this::updatePersonDetails);
                    }, Throwable::printStackTrace)));
        }else{
            addViewSubscription((((PersonsAdapter) personsRecyclerView.getAdapter())
                    .getPersonListItemClickr()
                    .subscribe(person -> {

                        navigateToPersonDetails(person);
                    }, Throwable::printStackTrace)));
        }
    }

    private void addViewSubscription(Disposable disposable) {
        subscriptions.add(disposable);
    }

    private void clearViewSubscription() {
        subscriptions.clear();
    }

    public void updatePersonDetails(PersonDetails personDetails) {
        personName.setText(personDetails.getName());
        personTeam.setText(personDetails.getTeam());

    }

    @Override
    public void onDetach() {
        clearViewSubscription();
        super.onDetach();
    }


    @Override
    public void updatePersonsList(List<Person> personList) {
        ((PersonsAdapter) personsRecyclerView.getAdapter()).setPersons(personList);
    }

    @Override
    public void onRefresh() {
        mViewModel.getPersons();
        swipeRefreshLayout.setRefreshing(false);
    }
}
