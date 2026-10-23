
package com.example.pertemuan4.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pertemuan4.R;
import com.example.pertemuan4.model.Person;
import com.example.pertemuan4.model.Mahasiswa;
import com.example.pertemuan4.model.Dosen;
import com.example.pertemuan4.model.EvaluasiAkademik;

import java.util.ArrayList;
import java.util.List;

public class AkademikAdapter
        extends RecyclerView.Adapter<AkademikAdapter.ViewHolder> {

    public interface Listener {
        void onEdit(Person person);
        void onDelete(Person person);
    }

    private final List<Person> data = new ArrayList<>();
    private final Listener listener;

    public AkademikAdapter(Listener listener) {
        this.listener = listener;
    }

    public void setData(List<? extends Person> items) {
        data.clear();
        if (items != null) {
            data.addAll(items);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_akademik, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder, int position) {

        Person person = data.get(position);

        holder.txtNama.setText(person.getNama());
        holder.txtRole.setText("Role: " + person.getRole());

        if (person instanceof EvaluasiAkademik) {
            EvaluasiAkademik evaluasi =
                    (EvaluasiAkademik) person;
            holder.txtStatus.setText(
                    "Status: " + evaluasi.getStatusAkademik());
        }

        if (person instanceof Mahasiswa) {
            Mahasiswa m = (Mahasiswa) person;
            holder.txtIdentitas.setText(
                    "NIM: " + m.getNim()
                            + "\nJurusan: " + m.getJurusan()
                            + "\nIPK: " + m.getIpk());
        } else if (person instanceof Dosen) {
            Dosen d = (Dosen) person;
            holder.txtIdentitas.setText(
                    "NIDN: " + d.getNidn()
                            + "\nMata kuliah: " + d.getMataKuliahDiampu());
        }

        holder.btnEdit.setOnClickListener(
                v -> listener.onEdit(person));

        holder.btnHapus.setOnClickListener(
                v -> listener.onDelete(person));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtIdentitas, txtRole, txtStatus;
        Button btnEdit, btnHapus;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.txtNama);
            txtIdentitas =
                    itemView.findViewById(R.id.txtIdentitas);
            txtRole = itemView.findViewById(R.id.txtRole);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnHapus = itemView.findViewById(R.id.btnHapus);
        }
    }
}
