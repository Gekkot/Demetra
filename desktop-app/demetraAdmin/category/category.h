#ifndef CATEGORY_H
#define CATEGORY_H

#include <QObject>
#include <QWidget>
#include <QLabel>
#include "categoryediter.h"
#include <QPushButton>
#include <QDebug>

class Category : public QWidget
{
    Q_OBJECT
public:
    explicit Category(QWidget *parent = 0, QString categoryName="NULL");
    QString getCategoryName();
    QPushButton* editNameButton; //Ай,ай,Миша,халтура

private:
    QPushButton* categoryNameButton;
    QPushButton* deleteCategoryButton;
    QPushButton* addPositionButton;

private slots:
    void editNameButtonEvent();
    void editName(QString newName);
    void deleteButtonEvent();
    void addPositionButtonClicked();
    void categoryNameButtonClicked();

signals:
    void deleteCategoryLater();
    void addPositionButtonEvent();
    void categoryViewClicked();

public slots:
};

#endif // CATEGORY_H
