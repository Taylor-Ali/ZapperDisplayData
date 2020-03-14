package com.leaf.zapperdisplaydata.ui.personsdetails.view;

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

import com.leaf.zapperdisplaydata.R;
import com.leaf.zapperdisplaydata.data.remote.model.PersonDetails;
import com.leaf.zapperdisplaydata.ui.personsdetails.PersonDetailsContract;
import com.leaf.zapperdisplaydata.ui.personsdetails.viewmodel.PersonDetailsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonDetailsFragment extends Fragment implements PersonDetailsContract.View {

    @BindView(R.id.person_name)
    TextView personName;

    @BindView(R.id.person_team)
    TextView personTeam;

    private PersonDetailsViewModel mViewModel;

    public static PersonDetailsFragment newInstance() {
        return new PersonDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_person_details, container, false);


        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PersonDetailsViewModel.class);

        mViewModel.getPersonDetail(getPersonId())
                .observe(getViewLifecycleOwner(), this::updatePersonDetails);

    }

    private Integer getPersonId() {
        Bundle bundle = PersonDetailsFragmentArgs.fromBundle(getArguments()).toBundle();
        return bundle.getInt("id");
    }

    @Override
    public void updatePersonDetails(PersonDetails personDetails) {
        personName.setText(personDetails.getName());
        personTeam.setText(personDetails.getTeam());

    }
}
