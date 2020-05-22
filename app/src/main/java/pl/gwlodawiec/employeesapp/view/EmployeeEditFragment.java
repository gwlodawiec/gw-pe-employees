package pl.gwlodawiec.employeesapp.view;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;
import java.util.Optional;

import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;
import pl.gwlodawiec.employeesapp.R;
import pl.gwlodawiec.employeesapp.model.Employee;
import pl.gwlodawiec.employeesapp.model.types.Gender;
import pl.gwlodawiec.employeesapp.service.EmployeeManager;
import pl.gwlodawiec.employeesapp.service.helper.EmployeeInput;
import pl.gwlodawiec.employeesapp.service.helper.EmployeeInputProcessor;
import pl.gwlodawiec.employeesapp.service.helper.EmployeeInputResponse;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmployeeEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmployeeEditFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EmployeeManager employeeManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EmployeeEditFragment() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmployeeEditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EmployeeEditFragment newInstance(String param1, String param2) {
        EmployeeEditFragment fragment = new EmployeeEditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //initialize EmployeeManager
        if(this.employeeManager == null){
            this.employeeManager = new EmployeeManager(getContext(), this);
        }

        View view = inflater.inflate(R.layout.fragment_employee_edit, container, false);
        Spinner gendersSpinner = view.findViewById(R.id.genders_spinner);
        gendersSpinner.setAdapter( new ArrayAdapter<Gender>(getContext(), android.R.layout.simple_list_item_1, Gender.values()));
        // Inflate the layout for this fragment
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.edit_view_prev_button).setOnClickListener(

                (view1) -> {
                    NavHostFragment.findNavController(EmployeeEditFragment.this)
                            .navigate(R.id.action_employeeEditFragment_to_FirstFragment);
                }
        );

        view.findViewById(R.id.save_button).setOnClickListener(
                (view2) -> {

                    EmployeeInputResponse inputProcessorRes = EmployeeInputProcessor.processEmployeeInput(view);

                    if(!inputProcessorRes.hasErrors()){
                        Optional<EmployeeInput> employeeInput = Optional.ofNullable(inputProcessorRes.getInput());
                        employeeInput.ifPresent(in -> {
                            employeeManager.addEmployee(in);
                        });

                        NavHostFragment.findNavController(EmployeeEditFragment.this)
                            .navigate(R.id.action_employeeEditFragment_to_FirstFragment);
                    }
                }
        );
    }

}
