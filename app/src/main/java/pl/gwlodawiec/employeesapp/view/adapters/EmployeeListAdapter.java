package pl.gwlodawiec.employeesapp.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import pl.gwlodawiec.employeesapp.R;
import pl.gwlodawiec.employeesapp.model.Employee;

/**
 * Adapter used for providing RecyclerView in employees data fetched from DB.
 */
public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder> {

    public interface OnDeleteClickListener {
        void onDeleteClickListener(Employee employee);
    }

    private final LayoutInflater layoutInflater;
    private Context context;
    private List<Employee> employeeList;
    private OnDeleteClickListener onDeleteClickListener;

    public EmployeeListAdapter(Context context, OnDeleteClickListener onDeleteClickListener){
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.onDeleteClickListener = onDeleteClickListener;
    }
    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.employee_layout, parent, false);
        EmployeeViewHolder viewHolder = new EmployeeViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        if(employeeList != null){
            Employee employee = employeeList.get(position);
            holder.setData(employee, position);
            holder.setListeners();
        }

    }

    @Override
    public int getItemCount() {
        if(employeeList == null || employeeList.isEmpty()){
            return 0;
        } else {
            return employeeList.size();
        }
    }

    public void setEmployees(List<Employee> employees){
        employeeList = employees;
        notifyDataSetChanged();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private TextView firstNameView;
        private TextView lastNameView;
        private TextView ageView;
        private ImageView deleteImg;
        private int mPosition;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            firstNameView = itemView.findViewById(R.id.firstname_view);
            lastNameView = itemView.findViewById(R.id.lastname_view);
            ageView = itemView.findViewById(R.id.age_view);
            deleteImg = itemView.findViewById(R.id.deleteImg);
        }

        public void setData(Employee employee, int position){
            firstNameView.setText(employee.getFirstName());
            lastNameView.setText(employee.getLastName());
            ageView.setText(Integer.toString(employee.getAge()));
            mPosition = position;
        }

        public void setListeners(){
            deleteImg.setOnClickListener(v -> {
                System.err.println("On delete clicked");
                if(onDeleteClickListener != null){
                    System.err.println("listener not null");
                    onDeleteClickListener.onDeleteClickListener(employeeList.get(mPosition));
                }
            });
        }
    }
}
