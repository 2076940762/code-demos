package services;

import java.util.List;

import doa.SearchKwDoa;

public class SearchKwService
    {

    public List<String> search(String kword) throws Exception {
        // TODO Auto-generated method stub
        return  new SearchKwDoa().search(kword);
    }

    }
