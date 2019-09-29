#ifndef CATEGORYCREATOR_H
#define CATEGORYCREATOR_H

#include <QObject>
#include <QWidget>
#include <QDebug>
#include <QLineEdit>
#include <QPushButton>
#include <QDialog>

class categoryCreator : public QDialog
{
    Q_OBJECT
public:
    explicit categoryCreator(QWidget *parent = 0);
    QPushButton* saveButton;
    QLineEdit* nameCategoryLineEdit;

signals:
    void saveCategoryName(QString name);

public slots:
    void saveButtonClicked();
};

#endif // CATEGORYCREATOR_H
