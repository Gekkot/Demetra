#ifndef CATEGORYEDITER_H
#define CATEGORYEDITER_H

#include <QObject>
#include <QWidget>
#include <QPushButton>
#include <QDebug>
#include <QLineEdit>


class categoryEditer : public QWidget
{
    Q_OBJECT
public:
    QPushButton* editButton;
    QLineEdit* nameCategoryLineEdit;
    explicit categoryEditer(QWidget *parent = 0);

signals:
    void changeCategoryName(QString newName);

public slots:
    void editButtonClicked();

};

#endif // CATEGORYEDITER_H
