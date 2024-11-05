package ru.techcoredev.store.dbconnect.DAOinterfeices;

import ru.techcoredev.store.objects.Status;

import java.util.List;

public interface StatusDAO {
    List<Status> getStatuses();
}
