package com.example.uipj.ui.fragments.manager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.uipj.R;
import com.example.uipj.adapter.group.ClassesAdapter;
import com.example.uipj.data.dao.GroupDAO;
import com.example.uipj.data.model.Group;
import com.example.uipj.databinding.FragmentClassesBinding;
import com.example.uipj.preferen.UserSharePreferences;
import com.example.uipj.ui.activities.auth.signin.SignInActivity;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClassesFragment extends Fragment {
    private FragmentClassesBinding binding;
    private UserSharePreferences userSharePreferences;
    private GroupDAO groupDAO;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupDAO = new GroupDAO(getActivity());
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentClassesBinding.inflate(inflater, container, false);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        activity.setSupportActionBar(binding.toolbar);
        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Group> listClasses = new ArrayList<>(groupDAO.getAllClasses());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false);
        binding.classesRv.setLayoutManager(linearLayoutManager);
        ClassesAdapter classesAdapter = new ClassesAdapter(requireActivity(), listClasses);
        binding.classesRv.setAdapter(classesAdapter);
        classesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}