#ifndef POSITIONS_H
#define POSITIONS_H

#include <QObject>
#include <QWidget>
#include <QPushButton>
#include <QDebug>
#include <QLabel>
#include <QFileDialog>
#include "positioneditor.h"

class positions : public QWidget
{
    Q_OBJECT
public:
    explicit positions(QWidget *parent = 0,QString name="NULL",QString cost="0");
    QString getPositionName();
    QString getIconPath();
    QString categoryID;
    void setCategoryID(QString nameCategory);
    QString getCategoryID();

private:
    QLabel* nameLabel;
    QLabel* costLabel;
    QPushButton* deletePositionButton;
    QPushButton* editPositionButton;
    QPushButton* addIconButton;
    QLabel* iconLabel;
    QString iconPath;

private slots:
    void editPostionButtonEvent();
    void edit(QString name,QString cost);
    void deleteButtonEvent();
    void loadIconLabelEvent();

signals:
    void deletePositionLater();

public slots:
};

#endif // POSITIONS_H
