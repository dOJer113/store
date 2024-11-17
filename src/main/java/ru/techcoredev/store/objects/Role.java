package ru.techcoredev.store.objects;

public enum Role {

    CLIENT {
        {
            this.url = "/client";
        }
    },

    ADMIN {
        {
            this.url = "/admin";
        }
    },
    NO_USER {
        {
            this.url = "index.jsp";
        }
    };
    public String url;

    public String getUrl() {
        return this.url;
    }
}


