package ru.tigran.cardcollector.database.entity;

import org.springframework.transaction.annotation.Transactional;
import ru.tigran.cardcollector.Utilities;
import ru.tigran.cardcollector.database.converters.EffectConverter;
import ru.tigran.cardcollector.database.converters.ExclusiveTaskConverter;
import ru.tigran.cardcollector.database.converters.IncomeTypeConverter;
import ru.tigran.cardcollector.enums.Effect;
import ru.tigran.cardcollector.enums.ExclusiveTask;
import ru.tigran.cardcollector.enums.IncomeType;
import ru.tigran.cardcollector.translations.Effects;
import ru.tigran.cardcollector.translations.ExclusiveTasks;

import javax.persistence.*;

@Entity
@Table(name = "stickers")
public class Sticker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 128, nullable = false)
    private String author;

    @Column(nullable = false)
    private Integer income;

    @Column(nullable = false)
    private Integer incomeTime;

    @Column(nullable = false)
    @Convert(converter = IncomeTypeConverter.class)
    public IncomeType incomeType = IncomeType.Coins;

    @Column(nullable = false)
    private Integer tier;

    @Column(nullable = false)
    @Convert(converter = EffectConverter.class)
    private Effect effect;

    @Column(nullable = false)
    @Convert(converter = ExclusiveTaskConverter.class)
    public ExclusiveTask exclusiveTask;

    @Column(length = 127, nullable = false)
    private String emoji;

    @Column(length = 1024, nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pack_id", nullable = false)
    private Pack pack;

    @Column(length = 127, nullable = false)
    private String fileId;

    @Column(nullable = false)
    private Boolean isAnimated;

    @Column(length = 127)
    private String forSaleFileId;

    @Column(length = 127)
    public String grayFileId;

    public int exclusiveTaskGoal;

    private Boolean isForSaleAnimated;

    @Column(length = 127)
    private String cacheFilePath;

    @Column(length = 127)
    private String cacheForSaleFilePath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getIncomeTime() {
        return incomeTime;
    }

    public void setIncomeTime(Integer incomeTime) {
        this.incomeTime = incomeTime;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public ru.tigran.cardcollector.enums.Effect getEffect() {
        return effect;
    }

    public void setEffect(ru.tigran.cardcollector.enums.Effect effect) {
        this.effect = effect;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Boolean isAnimated() {
        return isAnimated;
    }

    public void setAnimated(Boolean animated) {
        isAnimated = animated;
    }

    public String getForSaleFileId() {
        return forSaleFileId;
    }

    public void setForSaleFileId(String forSaleFileId) {
        this.forSaleFileId = forSaleFileId;
    }

    public Boolean isForSaleAnimated() {
        return isForSaleAnimated;
    }

    public void setForSaleAnimated(Boolean forSaleAnimated) {
        isForSaleAnimated = forSaleAnimated;
    }

    public String getCacheFilePath() {
        return cacheFilePath;
    }

    public void setCacheFilePath(String cacheFilePath) {
        this.cacheFilePath = cacheFilePath;
    }

    public String getCacheForSaleFilePath() {
        if (forSaleFileId == null) return getCacheFilePath();
        return cacheForSaleFilePath;
    }

    public void setCacheForSaleFilePath(String cacheForSaleFilePath) {
        this.cacheForSaleFilePath = cacheForSaleFilePath;
    }

    public IncomeType getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(IncomeType incomeType) {
        this.incomeType = incomeType;
    }

    public ExclusiveTask getExclusiveTask() {
        return exclusiveTask;
    }

    public void setExclusiveTask(ExclusiveTask exclusiveTask) {
        this.exclusiveTask = exclusiveTask;
    }

    public Boolean getAnimated() {
        return isAnimated;
    }

    public String getGrayFileId() {
        return grayFileId;
    }

    public void setGrayFileId(String grayFileId) {
        this.grayFileId = grayFileId;
    }

    public int getExclusiveTaskGoal() {
        return exclusiveTaskGoal;
    }

    public void setExclusiveTaskGoal(int exclusiveTaskGoal) {
        this.exclusiveTaskGoal = exclusiveTaskGoal;
    }

    public Boolean getForSaleAnimated() {
        return isForSaleAnimated;
    }

    public String getEffectDescription(){
        return Effects.get(effect.getValue());
    }

    public String getTaskDescription(){
        return String.format(ExclusiveTasks.get(exclusiveTask.getValue()), exclusiveTaskGoal);
    }

    public boolean isExclusive(){
        return tier == 10;
    }
}
