package com.leaf.zapperdisplaydata.ui.persons.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leaf.zapperdisplaydata.R;
import com.leaf.zapperdisplaydata.data.remote.model.Person;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.subjects.PublishSubject;

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.ViewHolder> {

    private final PublishSubject<Person> personListItemClick = PublishSubject.create();
    private List<Person> personList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item_persons, parent, false),
                personListItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.update(personList.get(position));
    }

    public void setPersons(List<Person> persons) {
        this.personList.clear();
        this.personList.addAll(persons);

        notifyDataSetChanged();
    }

    public PublishSubject<Person> getPersonListItemClickr() {
        return personListItemClick;
    }

    @Override
    public int getItemCount() {
        return personList != null ? personList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.person_item_container)
        RelativeLayout relativeLayout;

        @BindView(R.id.name)
        TextView person_name;

        private Person person;

        private PublishSubject<Person> personsListItemClick;


        public ViewHolder(@NonNull View itemView, PublishSubject<Person> personPublishSubject) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.personsListItemClick = personPublishSubject;
        }

        @OnClick(value = {R.id.person_item_container})
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.person_item_container:
                    personsListItemClick.onNext(person);
                    break;
            }
        }


        public void update(Person person) {
            this.person = person;

            person_name.setText(person.getName());

        }
    }
}
