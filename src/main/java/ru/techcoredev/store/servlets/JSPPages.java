package ru.techcoredev.store.servlets;


public enum JSPPages {

    ENTRY {
        {
            this.url = "/WEB-INF/view/entry.jsp";
        }
    },
    REGISTRATION {
        {
            this.url = "/WEB-INF/view/registration.jsp";
        }
    },
    ALL_USERS {
        {
            this.url = "/WEB-INF/view/viewAllUsers.jsp";
        }
    },
    ADMIN {
        {
            this.url = "/WEB-INF/view/adminMenu.jsp";
        }
    },
    USER_BY_ID {
        {
            this.url = "/WEB-INF/view/viewUserById.jsp";
        }
    };


    public String url;

    public String getUrl() {
        return this.url;
    }
}
