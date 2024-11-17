package ru.techcoredev.store.servlets;


public enum JSPPages {

    LOGIN {
        {
            this.url = "/WEB-INF/view/login.jsp";
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
    },
    SUCCESS{
        {
            this.url = "/WEB-INF/view/success.jsp";
        }
    };


    public String url;

    public String getUrl() {
        return this.url;
    }
}
