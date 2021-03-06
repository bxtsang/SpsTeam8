package com.google.sps.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

@Singleton
public class FirebaseUtil {
    @Inject
    public FirebaseUtil() throws Exception {
        // Fetch the service account key JSON file contents
        FileInputStream serviceAccount = new FileInputStream("./key.json");

        // Initialize the app with a service account, granting admin privileges
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://summer20-sps-47.firebaseio.com")
                .build();
        
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }

    public DatabaseReference getUserRoomReference() {
        return FirebaseDatabase.getInstance()
            .getReference("UserRoom");
    }

    public DatabaseReference getRoomsReference() {
        return FirebaseDatabase.getInstance()
                .getReference("rooms");
    }

    public DatabaseReference getOrdersReference() {
        return FirebaseDatabase.getInstance()
                .getReference("orders");
    }

    public DatabaseReference getMessagesReference() {
        return FirebaseDatabase.getInstance()
                .getReference("messages");
    }

    public Optional<DataSnapshot> getReferenceSnapshot(DatabaseReference ref) throws ServletException {
        final BlockingQueue<Optional<DataSnapshot>> queue = new LinkedBlockingDeque(1);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    queue.add(Optional.of(dataSnapshot));
                } else {
                    queue.add(Optional.empty());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        try {
            return queue.poll(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new ServletException("The database did not return a response for the specified reference");
        }
    }

    public Optional<DataSnapshot> getQuerySnapshot(Query query, String input) throws ServletException {
        final BlockingQueue<Optional<DataSnapshot>> queue = new LinkedBlockingDeque(1);
        query.equalTo(input).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    queue.add(Optional.of(dataSnapshot));
                } else {
                    queue.add(Optional.empty());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        try {
            return queue.poll(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new ServletException("The database did not return a response for the specified query");
        }
    }

    public List<DataSnapshot> getAllQuerySnapshots(Query query, String input) throws ServletException {
        final BlockingQueue<List<DataSnapshot>> queue = new LinkedBlockingDeque(1);
        List<DataSnapshot> list = new ArrayList<>();
        query.equalTo(input).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        list.add(postSnapshot);
                    }
                }
                queue.add(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        try {
            return queue.poll(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new ServletException("The database did not return a response for the specified query");
        }
    }

    public List<DataSnapshot> getAllSnapshotsFromReference(DatabaseReference databaseReference) throws ServletException {
        final BlockingQueue<List<DataSnapshot>> queue = new LinkedBlockingDeque<>(1);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<DataSnapshot> dataSnapshots = new ArrayList<>();
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    dataSnapshots.add(data);
                }
                queue.add(dataSnapshots);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        try {
            return queue.poll(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new ServletException("The database did not return a response for the specified reference");
        }
    }

    public void deleteFromDatabase(DatabaseReference ref, String key) throws ServletException {
        final BlockingQueue<Optional<ServletException>> queue = new LinkedBlockingDeque(1);
        ref.child(key).removeValue((databaseError, databaseReference) -> {
            if (databaseError != null) {
                queue.add(Optional.of(new ServletException("There was an error deleting an object from the database.")));
            } else {
                queue.add(Optional.empty());
            }
        });

        try {
            Optional<ServletException> servletException = queue.poll(30, TimeUnit.SECONDS);
            if (servletException.isPresent()) {
                throw servletException.get();
            }
        } catch (InterruptedException | ServletException e) {
            throw new ServletException("No response was returned by the database.");
        }
    }

    public void addToDatabase(DatabaseReference ref, Map<?, ?> mappings) throws ServletException {
        final BlockingQueue<Optional<ServletException>> queue = new LinkedBlockingDeque(1);
        ref.push()
            .setValue(mappings, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    queue.add(Optional.of(new ServletException("There was an error adding a new object to the database.")));
                } else {
                    queue.add(Optional.empty());
                }
            });

        try {
            Optional<ServletException> servletException = queue.poll(30, TimeUnit.SECONDS);
            if (servletException.isPresent()) {
                throw  servletException.get();
            }
        } catch (InterruptedException | ServletException e) {
            throw new ServletException("No response was returned by the database.");
        }
    }
}
