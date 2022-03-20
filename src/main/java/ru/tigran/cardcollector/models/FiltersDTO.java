package ru.tigran.cardcollector.models;

import org.springframework.lang.Nullable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FiltersDTO {
    @Nullable
    private Integer page;
    @Nullable
    private String author;
    @Nullable
    private Integer tier;
    @Nullable
    private String emoji;
    @Nullable
    private String sortBy;

    @Nullable
    public Integer getPage() {
        return page;
    }

    public void setPage(@Nullable Integer page) {
        this.page = page;
    }

    @Nullable
    public String getAuthor() {
        if (author == "") return null;
        return author;
    }

    public void setAuthor(@Nullable String author) {
        this.author = author;
    }

    @Nullable
    public Integer getTier() {
        return tier;
    }

    public void setTier(@Nullable Integer tier) {
        this.tier = tier;
    }

    @Nullable
    public String getEmoji() {
        if (emoji == "") return null;
        return emoji;
    }

    public void setEmoji(@Nullable String emoji) {
        this.emoji = emoji;
    }

    @Nullable
    public String getSortBy() {
        if (sortBy == "") return null;
        return sortBy;
    }

    public void setSortBy(@Nullable String sortBy) {
        this.sortBy = sortBy;
    }

    public String toStringDebug() {
        return "FiltersDTO{" +
                "page=" + page +
                ", author='" + author + '\'' +
                ", tier=" + tier +
                ", emoji='" + emoji + '\'' +
                ", sortBy='" + sortBy + '\'' +
                '}';
    }

    public String asUrl(String pathname) {
        StringBuilder sb = new StringBuilder(pathname);

        if (page != null) {
            sb.append("page=");
            sb.append(page);
            sb.append("&");
        }
        if (author != null) {
            sb.append("author=");
            sb.append(author);
            sb.append("&");
        }
        if (tier != null) {
            sb.append("tier=");
            sb.append(tier);
            sb.append("&");
        }
        if (emoji != null) {
            try{
                String encodedEmoji = URLEncoder.encode(emoji, StandardCharsets.UTF_8);
                sb.append("emoji=");
                sb.append(encodedEmoji);
                sb.append("&");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        if (sortBy != null) {
            sb.append("sortBy=");
            sb.append(sortBy);
            sb.append("&");
        }
        return sb.toString();
    }
}
